package com.mcompany.coupan.data.network.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mcompany.coupan.BuildConfig;
import com.mcompany.coupan.appcommon.constants.AuthConstants;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.logger.AppLogger;
import com.mcompany.coupan.dtos.EmptyDataModel;
import com.mcompany.coupan.dtos.FileUploadData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class VolleyMultipartRequest<T> extends Request<T> {
    private static final String TAG = VolleyMultipartRequest.class.getSimpleName();
    private final String twoHyphens = "--";
    private final String lineEnd = "\r\n";
    private final String boundary = "apiclient-" + System.currentTimeMillis();

    private final Gson mGson = new Gson();
    private final Map<String, String> mParams;
    private String mTAG;
    private final NetworkVolleyListener<T> mListener;
    private final Class<T> mClass;
    private final ArrayList<FileUploadData> mFileData;


    public VolleyMultipartRequest(Context context, int method, String url, Class<T> tClass, NetworkVolleyListener listener, Map<String, String> params, ArrayList<FileUploadData> fileUploadData, String tag) {
        super(method, url, listener);
        mClass = tClass;
        mListener = listener;
        mParams = params;
        mTAG = tag;
        mFileData = fileUploadData;

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = null;
        headers = new HashMap<String, String>(1);
        headers.put(AuthConstants.AUTHORIZATION, AuthConstants.BEARER + OauthManager.getInstance().getToken());
        headers.put(AuthConstants.USER_AGENT_KEY, AuthConstants.USER_AGENT_VALUE);
        return headers;
    }


    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);

    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data;boundary=" + boundary;
    }

    @Override
    public byte[] getBody() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            // populate text payload
            Map<String, String> params = mParams;
            if (params != null && params.size() > 0) {
                textParse(dos, params, getParamsEncoding());
            }

            // populate data byte payload
            Map<String, DataPart> data = getByteData();
            if (data != null && data.size() > 0) {
                dataParse(dos, data);
            }

            // close multipart form data after text and file data
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deliverError(VolleyError error) {
        mListener.onErrorResponse(error);
    }

    /**
     * Parse string map into data output stream by key and value.
     *
     * @param dataOutputStream data output stream handle string parsing
     * @param params           string inputs collection
     * @param encoding         encode the inputs, default UTF-8
     * @throws IOException
     */
    private void textParse(DataOutputStream dataOutputStream, Map<String, String> params, String encoding) throws IOException {
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                buildTextPart(dataOutputStream, entry.getKey(), entry.getValue());
            }
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + encoding, uee);
        }
    }

    /**
     * Parse data into data output stream.
     *
     * @param dataOutputStream data output stream handle file attachment
     * @param data             loop through data
     * @throws IOException
     */
    private void dataParse(DataOutputStream dataOutputStream, Map<String, DataPart> data) throws IOException {
        for (Map.Entry<String, DataPart> entry : data.entrySet()) {
            buildDataPart(dataOutputStream, entry.getValue(), entry.getKey());
        }
    }

    /**
     * Write string data into header and data output stream.
     *
     * @param dataOutputStream data output stream handle string parsing
     * @param parameterName    name of input
     * @param parameterValue   value of input
     * @throws IOException
     */
    private void buildTextPart(DataOutputStream dataOutputStream, String parameterName, String parameterValue) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + parameterName + "\"" + lineEnd);
        //dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);
        dataOutputStream.writeBytes(parameterValue + lineEnd);
    }

    /**
     * Write data file into header and data output stream.
     *
     * @param dataOutputStream data output stream handle data parsing
     * @param dataFile         data byte as DataPart from collection
     * @param inputName        name of data input
     * @throws IOException
     */
    private void buildDataPart(DataOutputStream dataOutputStream, DataPart dataFile, String inputName) throws IOException {
        dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                inputName + "\"; filename=\"" + dataFile.getFileName() + "\"" + lineEnd);
        if (dataFile.getType() != null && !dataFile.getType().trim().isEmpty()) {
            dataOutputStream.writeBytes("Content-Type: " + dataFile.getType() + lineEnd);
        }
        dataOutputStream.writeBytes(lineEnd);

        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(dataFile.getContent());
        int bytesAvailable = fileInputStream.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }

    /**
     * Simple data container use for passing byte file
     */
    public class DataPart {
        private String fileName;
        private byte[] content;
        private String type;

        /**
         * Default data part
         */
        public DataPart() {
        }

        /**
         * Constructor with data.
         *
         * @param name label of data
         * @param data byte data
         */
        public DataPart(String name, byte[] data) {
            fileName = name;
            content = data;
        }

        /**
         * Constructor with mime data type.
         *
         * @param name     label of data
         * @param data     byte data
         * @param mimeType mime data like "image/jpeg"
         */
        public DataPart(String name, byte[] data, String mimeType) {
            fileName = name;
            content = data;
            type = mimeType;
        }

        /**
         * Getter file name.
         *
         * @return file name
         */
        public String getFileName() {
            return fileName;
        }

        /**
         * Setter file name.
         *
         * @param fileName string file name
         */
        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        /**
         * Getter content.
         *
         * @return byte file data
         */
        public byte[] getContent() {
            return content;
        }

        /**
         * Setter content.
         *
         * @param content byte file data
         */
        public void setContent(byte[] content) {
            this.content = content;
        }

        /**
         * Getter mime type.
         *
         * @return mime type
         */
        public String getType() {
            return type;
        }

        /**
         * Setter mime type.
         *
         * @param type mime type
         */
        public void setType(String type) {
            this.type = type;
        }
    }


    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        return params;
    }


    public byte[] fileBytetoupload(String filePath) {
        File file = new File(filePath);

        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException e) {
            AppLogger.e(TAG, "File Not Found" + e.getMessage());
        } catch (IOException e1) {
            AppLogger.e(TAG, "Error Reading The File." + e1.getMessage());
        }
        return b;
    }

    protected Map<String, DataPart> getByteData() {
        Map<String, DataPart> params = new HashMap<>();
        // file name could found file base or direct access from real path
        // for now just get bitmap data from ImageView
        for (int i = 0; i < mFileData.size(); i++) {
            if (mTAG.equalsIgnoreCase("CRMFormInteractorImpl")) {
                params.put("files", new DataPart(mFileData.get(i).getFileName(), fileBytetoupload(mFileData.get(i).getFilePath())));
            } else {
                params.put("file", new DataPart(mFileData.get(i).getFileName(), fileBytetoupload(mFileData.get(i).getFilePath())));
            }
        }
        return params;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = null;
            EmptyDataModel emptyDataModel = null;
            json = new String(response.data, "UTF-8");
            if ((response.statusCode == NetworkHelper.HTTP_STATUS_CODE_OK || response.statusCode == NetworkHelper.HTTP_STATUS_CODE_CREATED || response.statusCode == NetworkHelper.HTTP_STATUS_CODE_ACCEPTED) && Constants.EMPTY_STRING.equalsIgnoreCase(json)) {
                emptyDataModel = new EmptyDataModel();
                emptyDataModel.setStatusCode(response.statusCode);
                emptyDataModel.setResponseObject(response.data);
            }
            if (BuildConfig.DEBUG)
                AppLogger.d(TAG, "Response :: " + response.statusCode == null ? null :
                        new String(response.data));
            if (Constants.EMPTY_STRING.equalsIgnoreCase(json)) {
                return (Response<T>) Response.success(emptyDataModel, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.success(mGson.fromJson(json, mClass), HttpHeaderParser
                        .parseCacheHeaders(response));
            }
        } catch (UnsupportedEncodingException ex) {
            if (BuildConfig.DEBUG) AppLogger.d(TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        } catch (JsonSyntaxException ex) {
            if (BuildConfig.DEBUG) AppLogger.d(TAG, ex.getMessage());
            return Response.error(new ParseError(ex));
        }
    }


}
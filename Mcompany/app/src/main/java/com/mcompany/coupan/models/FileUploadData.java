package com.mcompany.coupan.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by s53820 on 2/20/2017.
 */
public class FileUploadData implements Parcelable {
    private String fileName;
    private String filePath;
    private String fileExtension;
    private String viewTag;
    public FileUploadData(){}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fileName);
        parcel.writeString(filePath);
        parcel.writeString(fileExtension);
        parcel.writeString(viewTag);

    }

    protected FileUploadData(Parcel in) {
        fileName = in.readString();
        filePath = in.readString();
        fileExtension = in.readString();
        viewTag = in.readString();
    }

    public static final Creator<FileUploadData> CREATOR = new Creator<FileUploadData>() {
        @Override
        public FileUploadData createFromParcel(Parcel in) {
            return new FileUploadData(in);
        }

        @Override
        public FileUploadData[] newArray(int size) {
            return new FileUploadData[size];
        }
    };

    public String getViewTag() {
        return viewTag;
    }

    public void setViewTag(String viewTag) {
        this.viewTag = viewTag;
    }
}

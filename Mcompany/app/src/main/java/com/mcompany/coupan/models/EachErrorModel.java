package com.mcompany.coupan.models;

import android.os.Parcel;
import android.os.Parcelable;

public class EachErrorModel implements Parcelable {

	private String message;
	private String type;
	private String reason;

	public static Creator<EachErrorModel> getCREATOR() {
		return CREATOR;
	}



	public EachErrorModel() { }

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	protected EachErrorModel(Parcel in) {
		message = in.readString();
		type = in.readString();
		reason = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(message);
		dest.writeString(type);
		dest.writeString(reason);
	}

	@SuppressWarnings("unused")
	public static final Creator<EachErrorModel> CREATOR = new Creator<EachErrorModel>() {
		@Override
		public EachErrorModel createFromParcel(Parcel in) {
			return new EachErrorModel(in);
		}

		@Override
		public EachErrorModel[] newArray(int size) {
			return new EachErrorModel[size];
		}
	};
}

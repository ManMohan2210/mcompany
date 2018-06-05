package com.mcompany.coupan.dtos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class ErrorModel implements Parcelable {

	List<EachErrorModel> errors;

	public ErrorModel() { }

	public List<EachErrorModel> getErrors() {
		return errors;
	}

	public void setErrors(List<EachErrorModel> errors) {
		this.errors = errors;
	}



	protected ErrorModel(Parcel in) {
		if (in.readByte() == 0x01) {
			errors = new ArrayList<>();
			in.readList(errors, EachErrorModel.class.getClassLoader());
		} else {
			errors = null;
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (errors == null) {
			dest.writeByte((byte) (0x00));
		} else {
			dest.writeByte((byte) (0x01));
			dest.writeList(errors);
		}
	}

	@SuppressWarnings("unused")
	public static final Creator<ErrorModel> CREATOR = new Creator<ErrorModel>() {
		@Override
		public ErrorModel createFromParcel(Parcel in) {
			return new ErrorModel(in);
		}

		@Override
		public ErrorModel[] newArray(int size) {
			return new ErrorModel[size];
		}
	};
}

package com.example.fragmentpractice;

public class QuickText {
	private int _id;
	private String text;

	public QuickText(String text) {
		this._id = QuickTextsManager.nextQuickTextID;
		this.text = text;
	}
	
	public QuickText(int _id, String text) {
		this._id = _id;
		this.text = text;
	}

	public String getQuickText() {
		return text;
	}

	public int getID() {
		return _id;
	}

	public void editText(String text) {
		this.text = text;
	}
}

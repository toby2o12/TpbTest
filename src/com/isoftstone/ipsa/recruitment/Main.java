package com.isoftstone.ipsa.recruitment;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username="hxtu";
		String password="123qwe";
		CreateResumeTest createResumeTest=new CreateResumeTest();
		createResumeTest.login(username, password);
		createResumeTest.enterCreateResume();
		createResumeTest.createResume("F:\\autotestdoc\\陈风帆.doc", "13849069020");
		createResumeTest.createResume("F:\\autotestdoc\\王擎.doc", "13922820805");
	}

}

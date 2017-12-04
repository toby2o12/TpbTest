package com.isoftstone.ipsa.recruitment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateResumeTest extends BaseClass {
	public void enterCreateResume(){
		Actions action = new Actions(driver);
		action.moveToElement(getElementById("resumeManage")).perform();
		WebElement createResume =getElementByXPath(".//*[@id='resumeManage']/ul/a[3]/li");
		createResume.click();
		wait(8000);
	}
	
	public void createResume(String filePath, String phone) {
		String phoneNum = phone;
		String PromptOK;
		WebElement uploadResume = getElementByCss("input[type=file]");
		uploadResume.sendKeys(filePath);
		wait(50000);
		WebElement modalAlert = getElementById("modalAlert");
		String modalMessage = "";
		if (modalAlert.isDisplayed()) {
			modalMessage = ".//*[@id='modalAlert']/div/div/div[2]/div/em";
			PromptOK = ".//*[@id='modalAlertOk']";
			System.out.println("Alert提示框");
		} else {
			modalMessage = ".//*[@id='modalConfirm']/div/div/div[2]/div/em";
			PromptOK = ".//*[@id='modalConfirmOk']";
			System.out.println("Confirm提示框");
		}
		WebElement verifyPrompt = getElementByXPath(modalMessage);
		String promptContent = verifyPrompt.getText();
		System.out.println("简历校验提示：" + promptContent);
		WebElement verifyPromptOK = getElementByXPath(PromptOK);
		if (promptContent.equals("简历已在自己名下锁定，无法创建！")) {
			verifyPromptOK.click();
			wait(8000);
		} else if (promptContent.equals("简历正在被他人使用中，无法创建！")) {
			verifyPromptOK.click();
			wait(8000);
		} else if (promptContent.equals("简历库中有一份相同的简历，请确认是否入库？")) {
			verifyPromptOK.click();
			wait(8000);
			createSuccess();
			searchResume(phoneNum);
		} else if (promptContent.equals("简历创建成功")) {
			createSuccess();
			searchResume(phoneNum);
		} else {
			System.out.println("创建简历提示异常！！！");
			verifyPromptOK.click();
			System.out.println("创建简历用例执行：fail");
		}
	}

	public void createSuccess() {
		WebElement createOkAlert = driver.findElement(By
				.xpath(".//*[@id='modalAlert']/div/div/div[2]/div/em"));
		String createOkAlertCon = createOkAlert.getText();
		if (createOkAlertCon.equals("简历创建成功")) {
			System.out.println("简历创建成功：pass");
		} else {
			System.out.println("简历创建成功：fail");
			return;
		}
		WebElement createOk = driver.findElement(By
				.xpath(".//*[@id='modalAlertOk']"));
		createOk.click();
		wait(5000);
		System.out.println("创建成功");
		// 【保存并完成】简历
		WebElement saveResume = driver.findElement(By
				.xpath(".//*[@id='resumeSaveOrSaveRecommend']"));
		saveResume.click();
		wait(8000);
		// 【我知道了】确认
		WebElement iKnow = driver.findElement(By
				.xpath(".//*[@id='resume_step_save']/p/a"));
		iKnow.click();
		wait(8000);

	}

	public void searchResume(String phoneNum) {
		getElementById("searchinput").sendKeys(phoneNum);
		getElementByXPath(".//*[@id='seek']/form/a[1]").click();

		WebElement DataElement = getElementByCss(".UserContentList > ul > li.Infor .Informations");
		boolean newisDisplayed = DataElement.isDisplayed();
		if (newisDisplayed) {
			System.out.println("简历创建成功，同步到搜索引擎：pass");
		} else {
			System.out.println("简历创建成功，同步到搜索引擎：fail");
		}
		wait(5000);
		Actions action = new Actions(driver);
		action.moveToElement(getElementById("resumeManage")).perform();
		WebElement createResume = getElementByXPath(".//*[@id='resumeManage']/ul/a[3]/li");
		createResume.click();
		// 等待页面加载
		wait(8000);
	}

}

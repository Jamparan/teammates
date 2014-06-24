package teammates.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import teammates.common.util.Const;

public class FeedbackSubmitPage extends AppPage {

    public FeedbackSubmitPage(Browser browser) {
        super(browser);
    }

    @Override
    protected boolean containsExpectedPageContents() {
        return getPageSource().contains("<h1>Submit Feedback</h1>");
    }
    
    public String getCourseId() {
        return browser.driver.findElement(By.name("courseid")).getAttribute("value");
    }
    
    public String getFeedbackSessionName() {
        return browser.driver.findElement(By.name("fsname")).getAttribute("value");
    }
    
    public boolean isCorrectPage (String courseId, String feedbackSessionName) {
        boolean isCorrectCourseId = this.getCourseId().equals(courseId);
        boolean isCorrectFeedbackSessionName = this.getFeedbackSessionName().equals(feedbackSessionName);
        return isCorrectCourseId && isCorrectFeedbackSessionName && containsExpectedPageContents();
    }
    
    public void selectRecipient(int qnNumber, int responseNumber, String recipientName) {
        browser.selenium.select("name=" + Const.ParamsNames.FEEDBACK_RESPONSE_RECIPIENT + 
                "-" + qnNumber + "-" + responseNumber, "label=" + recipientName);
    }
    
    public void fillResponseTextBox(int qnNumber, int responseNumber, String text) {
        WebElement element = browser.driver.findElement(
                By.name(Const.ParamsNames.FEEDBACK_RESPONSE_TEXT + "-" + qnNumber + "-" + responseNumber));
        element.click();
        fillTextBox(element, text);
    }
    
    public void fillResponseTextBox(int qnNumber, int responseNumber, int responseSubNumber, String text) {
        WebElement element = browser.driver.findElement(
                By.id(Const.ParamsNames.FEEDBACK_RESPONSE_TEXT + "-" + qnNumber + "-" + responseNumber + "-" + responseSubNumber));
        element.click();
        fillTextBox(element, text);
    }
    
    public String getResponseTextBoxValue(int qnNumber, int responseNumber) {
        WebElement element = browser.driver.findElement(
                By.name(Const.ParamsNames.FEEDBACK_RESPONSE_TEXT + "-" + qnNumber + "-" + responseNumber));
        return element.getAttribute("value");
    }
    
    public String getConstSumMessage(int qnNumber, int responseNumber) {
        WebElement element = browser.driver.findElement(
                By.id("constSumMessage-" + qnNumber + "-" + responseNumber));
        return element.getText();
    }
    
    public void chooseMcqOption(int qnNumber, int responseNumber, String choiceName){
        String name = Const.ParamsNames.FEEDBACK_RESPONSE_TEXT + "-" + qnNumber + "-" + responseNumber;
        WebElement element = browser.driver.findElement(By.xpath("//input[@name='" + name + "' and @value='" + choiceName + "']"));
        element.click();
    }
    
    public void toggleMsqOption(int qnNumber, int responseNumber, String choiceName){
        String name = Const.ParamsNames.FEEDBACK_RESPONSE_TEXT + "-" + qnNumber + "-" + responseNumber;
        WebElement element = browser.driver.findElement(By.xpath("//input[@name='" + name + "' and @value='" + choiceName + "']"));
        element.click();
    }
    
    public void clickSubmitButton() {
        WebElement button = browser.driver.findElement(By.id("response_submit_button"));
        button.click();
    }

}

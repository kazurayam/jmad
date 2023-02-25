import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.nio.file.Path
import java.nio.file.Paths
import org.openqa.selenium.WebElement

TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}

Path html = Paths.get("Остановки 1 часть.html")

WebUI.openBrowser("")
WebUI.setViewPortSize(800, 600)
WebUI.navigateToUrl(html.toFile().toURI().toURL().toExternalForm())

TestObject TO_testsuite = makeTestObject("//div[@id='s1']")
WebUI.verifyElementPresent(TO_testsuite, 10)
WebUI.click(TO_testsuite)

TestObject TO_testcase = makeTestObject("//div[@class='test']/div/span[3]/span[1]/span[1]")
List<WebElement> dataElements = WebUI.findWebElements(TO_testcase, 10)
assert dataElements.size() > 1000
StringBuilder sb = new StringBuilder()
dataElements.each { WebElement el ->
	sb.append(el.getText())
	sb.append("\n")	
}
File data = new File("./data.csv")
data.text = sb.toString()

WebUI.closeBrowser()
WebUI.comment("done")

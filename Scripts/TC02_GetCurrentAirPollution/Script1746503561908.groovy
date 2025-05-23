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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper
import static org.assertj.core.api.Assertions.*
import static org.assertj.core.data.Offset.offset
def response = WS.sendRequest(findTestObject('Get_AirPollution'))
def expectedLat = GlobalVariable.lat.toFloat()
def expectedLon = GlobalVariable.lon.toFloat()

WS.verifyResponseStatusCode(response, 200)

// Parse response JSON
def json = new JsonSlurper().parseText(response.getResponseText())
assertThat(json).containsKeys("coord", "list")
assertThat(json.coord.lat).isNotNull()
assertThat(json.coord.lon).isNotNull()
assertThat(json.coord.lat).isCloseTo(expectedLat, offset(0.0001))
assertThat(json.coord.lon).isCloseTo(expectedLon, offset(0.0001))
//assert array of list	
def airData = json.list[0]
assertThat(airData).containsKeys("main", "components", "dt")
assertThat(airData.main.aqi).isBetween(1, 5)
//assert array of components
def comp = airData.components
["co", "no", "no2", "o3", "so2", "pm2_5", "pm10", "nh3"].each { key ->
	assertThat(comp).containsKey(key)
	assertThat(comp[key]).isInstanceOf(Number)
}
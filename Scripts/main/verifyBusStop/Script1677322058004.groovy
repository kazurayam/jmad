import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.comment(Bus_Stop)

if (Bus_Stop.contains("ный")) {
	KeywordUtil.markFailed("Mind ${Bus_Stop}")
}
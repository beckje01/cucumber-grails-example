package pages.screencast

import geb.Page

class ScreencastPage extends Page {
	static url = "screencast/list"

	static at = {
		title ==~ /Screencast List/
	}

	static content = {
	}
}
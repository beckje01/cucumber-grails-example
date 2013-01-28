package data

import example.Screencast


class Data {

	static def screencasts = [
		[name: "Some Screencast", description: "Some description"]
	]

	static public def findScreencast(String name) {
		screencasts.find{ screencast->
			screencast.name == name
		}
	}


	static void clearScreencasts () {
		Screencast.findAll()*.delete(flush: true)

	}

}

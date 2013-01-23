package features.step_definitions

import cucumber.runtime.PendingException
import data.Data
import example.Screencast
import example.ScreencastController

import static cucumber.api.groovy.EN.*

Given(~'^I am a guest$') { ->
	//nop
}
Given(~'^a screencast exist$') { ->
	def screencastData = Data.findScreencast("Some Screencast")
	Screencast screencast = new Screencast(screencastData)
	screencast.save()

}

def model = [:]

When(~'^I go to the screencast catalog$') { ->
	def controller = new ScreencastController()

	model =  controller.list()

}


Then(~'^I should see the screencast$') { ->

	def screencasts = model.screencastInstanceList
	def count = model.screencastInstanceTotal

	def expected = Data.findScreencast("Some Screencast")
	def actual = screencasts[0]

	assert count == 1
	assert actual.name == expected.name

}
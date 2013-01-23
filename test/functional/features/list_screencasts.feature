@integration
Feature: List Screencasts

	In order to find screencasts
	As a guest or user
	I Want to view a list of screencasts

	Scenario: Guest viewing list of screen casts
		Given I am a guest
		And a screencast exist
		When I go to the screencast catalog
		Then I should see the screencast
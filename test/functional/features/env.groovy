package features

import data.Data

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before



Before() {

	Data.clearScreencasts()
}

After() {

}
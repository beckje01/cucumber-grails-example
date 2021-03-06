package features

import data.Data
import geb.Browser
import geb.binding.BindingUpdater

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

Before() {
	bindingUpdater = new BindingUpdater(binding, new Browser())
	bindingUpdater.initialize()
}

After() {
	bindingUpdater.remove()
}


Before() {
	bindingUpdater = new BindingUpdater(binding, new Browser())
	bindingUpdater.initialize()
	Data.clearScreencasts()
}

After() {
	bindingUpdater.remove()

}
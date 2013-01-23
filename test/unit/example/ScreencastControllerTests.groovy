package example



import org.junit.*
import grails.test.mixin.*

@TestFor(ScreencastController)
@Mock(Screencast)
class ScreencastControllerTests {

	def populateValidParams(params) {
		assert params != null
		params["name"] = 'someValidName'
		params["description"] = "Description"
	}

	void testIndex() {
		controller.index()
		assert "/screencast/list" == response.redirectedUrl
	}

	void testList() {

		def model = controller.list()

		assert model.screencastInstanceList.size() == 0
		assert model.screencastInstanceTotal == 0
	}

	void testCreate() {
		def model = controller.create()

		assert model.screencastInstance != null
	}

	void testSave() {
		controller.save()

		assert model.screencastInstance != null
		assert view == '/screencast/create'

		response.reset()

		populateValidParams(params)
		controller.save()

		assert response.redirectedUrl == '/screencast/show/1'
		assert controller.flash.message != null
		assert Screencast.count() == 1
	}

	void testShow() {
		controller.show()

		assert flash.message != null
		assert response.redirectedUrl == '/screencast/list'

		populateValidParams(params)
		def screencast = new Screencast(params)

		assert screencast.save() != null

		params.id = screencast.id

		def model = controller.show()

		assert model.screencastInstance == screencast
	}

	void testEdit() {
		controller.edit()

		assert flash.message != null
		assert response.redirectedUrl == '/screencast/list'

		populateValidParams(params)
		def screencast = new Screencast(params)

		assert screencast.save() != null

		params.id = screencast.id

		def model = controller.edit()

		assert model.screencastInstance == screencast
	}

	void testUpdate() {
		controller.update()

		assert flash.message != null
		assert response.redirectedUrl == '/screencast/list'

		response.reset()

		populateValidParams(params)
		def screencast = new Screencast(params)

		assert screencast.save() != null

		// test invalid parameters in update
		params.id = screencast.id

		params["description"] = ""


		controller.update()

		assert view == "/screencast/edit"
		assert model.screencastInstance != null

		screencast.clearErrors()

		populateValidParams(params)
		controller.update()

		assert response.redirectedUrl == "/screencast/show/$screencast.id"
		assert flash.message != null

		//test outdated version number
		response.reset()
		screencast.clearErrors()

		populateValidParams(params)
		params.id = screencast.id
		params.version = -1
		controller.update()

		assert view == "/screencast/edit"
		assert model.screencastInstance != null
		assert model.screencastInstance.errors.getFieldError('version')
		assert flash.message != null
	}

	void testDelete() {
		controller.delete()
		assert flash.message != null
		assert response.redirectedUrl == '/screencast/list'

		response.reset()

		populateValidParams(params)
		def screencast = new Screencast(params)

		assert screencast.save() != null
		assert Screencast.count() == 1

		params.id = screencast.id

		controller.delete()

		assert Screencast.count() == 0
		assert Screencast.get(screencast.id) == null
		assert response.redirectedUrl == '/screencast/list'
	}
}

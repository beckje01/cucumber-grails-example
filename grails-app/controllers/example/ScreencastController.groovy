package example

import org.springframework.dao.DataIntegrityViolationException

class ScreencastController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[screencastInstanceList: Screencast.list(params), screencastInstanceTotal: Screencast.count()]
	}

	def create() {
		[screencastInstance: new Screencast(params)]
	}

	def save() {
		def screencastInstance = new Screencast(params)
		if (!screencastInstance.save(flush: true)) {
			render(view: "create", model: [screencastInstance: screencastInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'screencast.label', default: 'Screencast'), screencastInstance.id])
		redirect(action: "show", id: screencastInstance.id)
	}

	def show(Long id) {
		def screencastInstance = Screencast.get(id)
		if (!screencastInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'screencast.label', default: 'Screencast'), id])
			redirect(action: "list")
			return
		}

		[screencastInstance: screencastInstance]
	}

	def edit(Long id) {
		def screencastInstance = Screencast.get(id)
		if (!screencastInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'screencast.label', default: 'Screencast'), id])
			redirect(action: "list")
			return
		}

		[screencastInstance: screencastInstance]
	}

	def update(Long id, Long version) {
		def screencastInstance = Screencast.get(id)
		if (!screencastInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'screencast.label', default: 'Screencast'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (screencastInstance.version > version) {
				screencastInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
					[message(code: 'screencast.label', default: 'Screencast')] as Object[],
					"Another user has updated this Screencast while you were editing")
				render(view: "edit", model: [screencastInstance: screencastInstance])
				return
			}
		}

		screencastInstance.properties = params

		if (!screencastInstance.save(flush: true)) {
			render(view: "edit", model: [screencastInstance: screencastInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'screencast.label', default: 'Screencast'), screencastInstance.id])
		redirect(action: "show", id: screencastInstance.id)
	}

	def delete(Long id) {
		def screencastInstance = Screencast.get(id)
		if (!screencastInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'screencast.label', default: 'Screencast'), id])
			redirect(action: "list")
			return
		}

		try {
			screencastInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'screencast.label', default: 'Screencast'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'screencast.label', default: 'Screencast'), id])
			redirect(action: "show", id: id)
		}
	}
}

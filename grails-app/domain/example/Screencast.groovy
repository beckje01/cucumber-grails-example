package example


class Screencast {

	String name
	String description

	static constraints = {
		name blank: false
		description blank: false
	}
}

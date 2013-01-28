<%@ page import="example.Screencast" %>



<div class="fieldcontain ${hasErrors(bean: screencastInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="screencast.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${screencastInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: screencastInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="screencast.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${screencastInstance?.description}"/>
</div>


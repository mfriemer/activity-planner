package activityPlanner

import grails.rest.Resource 

@Resource(uri="/activities")
class Activity {
	long id
	String name

    static constraints = {
    }
}

class UrlMappings {

    static mappings = {
        "/location/$postalCode/activityPlans"(controller:"activityPlan", method:"GET", includes:['index'])
        "/location/$state/$city/activityPlans" {
		     controller = "activityPlan"
			 method = "GET"
		     action = "index"
		     constraints {
		          state(matches:/[A-Z]{2}/)
				  city(matches:/[A-Za-z]+.*/)
		     }
		}
		"/location/$latitude/$longitude/activityPlans" {
			controller = "activityPlan"
			method = "GET"
		    action = "index"
			constraints {
				latitude(matches:/-?\d+.?\d*/)
				longitude(matches:/-?\d+.?\d*/)
			}
		}
		"500"(view:'/error')
	}
}

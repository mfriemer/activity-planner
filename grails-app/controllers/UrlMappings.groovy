class UrlMappings {

    static mappings = {
        "/activities"(resources:"activity", method:"GET")
        "/location/$postalCode/activities"(resources:"activity", method:"GET", includes:['index'])
        "/location/$state/$city/activities" {
		     controller = "activity"
			 method = "GET"
		     action = "index"
		     constraints {
		          state(matches:/[A-Z]{2}/)
				  city(matches:/[A-Za-z]+.*/)
		     }
		}
		"/location/$latitude/$longitude/activities" {
			controller = "activity"
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

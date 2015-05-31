package activityPlanner

import activityPlanner.rating.ActivityRating
import activityPlanner.rating.ActivityRatingDelegate
import spock.lang.Specification

class ActivityRatingDelegateSpec extends Specification {
	def "Test delegate"() {
		List<ActivityRating> ratings = []
		ActivityRatingDelegate delegate = new ActivityRatingDelegate(ratings) 
		
		Closure cl = {
			activity "jogging", {
				tempRange 32, 65
			}
			activity "swimming", {
				tempRange 75, 95
				excludeConditions "rain", "snow"
			}
		}
		
		cl.delegate = delegate
		cl.resolveStrategy = Closure.DELEGATE_FIRST
		
		cl()
		
		expect:
		ratings.size() > 0
		ratings.any { it -> it.activityName == "jogging" }
		ratings.any { it -> it.activityName == "swimming" }
	}
}

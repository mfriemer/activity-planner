activities {
	activity "skiing", {
		tempRange 0, 32
	}
	activity "jogging", {
		tempRange 32, 65
	}
	activity "swimming", {
		tempRange 75, 95
		excludeConditions "rain", "snow", "storm"
	}
	activity "golf", {
		tempRange 50, 85
		windSpeedRange 0, 15
		excludeConditions "rain", "snow", "storm"
	}
	activity "flying a kite", {
		tempRange 50, 85
		windSpeedRange 10, 35
		excludeConditions "rain", "snow", "storm"
	}
	activity "splashing in puddles", {
		tempRange 32, 85
		excludeConditions "sunny", "cloudy"
	}
}
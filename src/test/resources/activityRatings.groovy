activities {
	activity "skiing", {
		tempRange 0, 32
	}
	activity "jogging", {
		tempRange 32, 65
	}
	activity "swimming", {
		tempRange 75, 95
		excludeConditions "rain", "snow"
	}
	activity "golf", {
		tempRange 50, 75
		windSpeedRange 0, 15
		excludeConditions "rain", "snow"
	}
	activity "flying a kite", {
		tempRange 50, 75
		windSpeedRange 15, 35
		excludeConditions "rain", "snow"
	}
}
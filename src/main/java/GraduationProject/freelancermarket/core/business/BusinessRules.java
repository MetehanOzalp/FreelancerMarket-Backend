package GraduationProject.freelancermarket.core.business;

import GraduationProject.freelancermarket.utils.Result;

public class BusinessRules {

	public static Result run(Result... logics) {
		for (Result result : logics) {
			if (!result.isSuccess()) {
				return result;
			}
		}
		return null;
	}

}

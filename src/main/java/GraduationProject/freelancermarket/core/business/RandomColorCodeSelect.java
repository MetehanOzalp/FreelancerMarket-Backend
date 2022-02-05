package GraduationProject.freelancermarket.core.business;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomColorCodeSelect {

	public static String randomColorCodeSelect() {
		List<String> colorCodes = Arrays.asList(new String[] { "0097a7", "4287f5", "eb4034", "fcba03", "32a852",
				"a903fc", "fc035a", "03adfc", "03fca9", "103783", "432371", "ef709b", "30c67c", "8364e8", "d397fa",
				"1dbde6", "57ebde", "b2ef91", "ff930f", "ff0f7b", "f89b29", "95ecb0", "3e3b92", "145277", "557c93",
				"bc1b68", "5c73b9", "c81d77", "4b749f", "35518a", "239eab", "0a3431", "436c89", "505da0", "074170" });
		Random random = new Random();
		int index = random.nextInt(colorCodes.size() - 1) + 0;
		return colorCodes.get(index);
	}

}

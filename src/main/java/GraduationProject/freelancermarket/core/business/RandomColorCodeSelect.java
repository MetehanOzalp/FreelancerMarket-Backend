package GraduationProject.freelancermarket.core.business;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomColorCodeSelect {

	public static String randomColorCodeSelect() {
		List<String> colorCodes = Arrays.asList(new String[] { "0097a7", "4287f5", "eb4034", "fcba03", "32a852",
				"a903fc", "fc035a", "03adfc", "03fca9" });
		Random random = new Random();
		int index = random.nextInt(colorCodes.size() - 1) + 0;
		return colorCodes.get(index);
	}

}

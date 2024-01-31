import java.util.Comparator;

public class ScoreComparator implements Comparator<PlayerForSort> {

	@Override
	public int compare(PlayerForSort o1, PlayerForSort o2) {
		return o2.highScore - o1.highScore;
	}

}

package status;

import logic.CollisionManager;
import logic.LogicManager;
import view.ScreenManager;

public class GameStatus implements Status {

	@Override
	public void doLoop(long delta) {
		CollisionManager.getInstance().doCollisions();
		LogicManager.getInstance().doLogic(delta);
		ScreenManager.getInstance().update(delta);
	}

}

package plugin.EnemyDownApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import plugin.EnemyDownApp.mapper.data.PlayerScore;
import plugin.EnemyDownApp.service.PLayerScoreService;

//
@RestController
public class PlayerScoreController {


  private PLayerScoreService service;

  public PlayerScoreController(PLayerScoreService service) {
    this.service = service;
  }

  @RequestMapping(value = "/playerScoreList", method = RequestMethod.GET)
  public List<PlayerScore> playerScoreList() {
    return service.searchPlayerScoreList();
  }
}

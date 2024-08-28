package plugin.EnemyDownApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.SpawnEnemy;
import plugin.EnemyDownApp.service.ConfigService;


//コントローラークラスはクライアントとサーバーをつなぐ窓口のような役割。
//画面からの要求を受け取り、要求に応じたサービスクラスを呼び出す。
@RestController
public class ConfigController {


  @Autowired
  private final ConfigService service;

  public ConfigController(ConfigService service) {
    this.service = service;
  }

  //@RequestMappingのデフォルトHTTPSメソッドはGET。"RequestMethod.GET"は不要。
  @GetMapping(value = "/configList")
  public List<GameConfig> searchConfigList() {
    return service.searchConfigList();
  }

  @GetMapping(value = "/config")
  public GameConfig searchConfig(@RequestParam String difficulty) {
    return service.searchConfig(difficulty);
  }

  @GetMapping(value = "/spawnEnemyList")
  public List<SpawnEnemy> spawnEnemyList(@RequestParam String difficulty) {
    return service.searchSpawnEnemyList(difficulty);
  }

  //serviceクラス①を呼び出す。(controller→service→mapper)
  //@PostMappingを使用することで、"RequestMethod.POST"は不要。
  //"config"URLを受けてHTTPSメソッドのPOSTが動く。
  @PostMapping(value = "/config")
  //@RequestBody GameConfig config；クライアントから送られてきたデータ（JSON形式）が、
  // GameConfigというJavaオブジェクトに変換されて、configという変数に挿入される。
  //ResponseEntity<GameConfig>：処理が失敗か成功かをレスポンスする。
  public ResponseEntity<GameConfig> registerConfig(@RequestBody GameConfig config) {

  //出来上がったGameConfigオブジェクトをserviceのregisterConfigに登録される。
  //GameConfig registerConfig：serviceクラスで返ってきた登録したconfig情報がかえってくる。それをResponseEntityの中に含める。
  //mapperクラスでconfig情報が登録される→serviceクラスで検索かける→検索結果を返す
  //RequestBodyのconfig情報にはid情報が含まれない？
    GameConfig registerConfig =  service.registerConfig(config);
    return new ResponseEntity<>(registerConfig, HttpStatus.OK);
  }

}

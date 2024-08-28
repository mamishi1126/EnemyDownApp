//mapperで受けとったデータを加工するのが、serviceクラスとcontrollerクラス
//serviceクラス⇒内側で高度なデータ加工を行う。
//controllerクラスはユーザーに見える部分の処理を行う。


package plugin.EnemyDownApp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import plugin.EnemyDownApp.mapper.GameConfigMapper;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.SpawnEnemy;

//具体的な処理(ビジネスロジック)を扱うクラス。
//サービスクラスがデータベースにアクセスが必要な際に、
// サービスクラスからmapperクラス(Spring Data JPAの場合はリポジトリクラス)を呼び出す。=エンティティクラス

//mapperクラスやリポジトリクラスが直接データベース操作を行う。
@Service
public class ConfigService {

  public final GameConfigMapper mapper;

  public ConfigService(GameConfigMapper mapper){
    this.mapper = mapper;
  }

  public List<GameConfig> searchConfigList() {
    return mapper.selectConfigList();
  }

  public GameConfig searchConfig(String difficulty) {
    return mapper.selectConfig(difficulty);
  }

  public List<SpawnEnemy> searchSpawnEnemyList(String difficulty) {
    return mapper.selectSpawnEnemyList(difficulty);
  }

  //①mapperクラス①を呼び出す。
  //今回は返り値の件数に意味を持たせないためvoidを使用。件数に意味を持たせる場合はintを使用。
  public GameConfig registerConfig(GameConfig config) {
    mapper.insertConfig(config);

    //mapperでinsertが終了したらもう一度mapperを呼び出し、
    // 今回設定された難易度の文字列に検索をかけて(selectConfig)、検索結果を返す。
    return mapper.selectConfig(config.getDifficulty());
  }
}

//mapperクラスはあくまでデータをとってくるだけのインターフェース。

package plugin.EnemyDownApp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.PlayerScore;
import plugin.EnemyDownApp.mapper.data.SpawnEnemy;

@Mapper
public interface GameConfigMapper {

  //SQLのselectでプレイヤースコア情報を抽出してくる。
  @Select("select * from game_config order by id asc")
  //抽出したプレイヤースコア情報をマッピングしてセレクトリストにして返す。
  List<GameConfig> selectConfigList();

  @Select("select * from game_config where difficulty = #{difficulty} order by id asc")
    //抽出したプレイヤースコア情報をマッピングしてセレクトリストにして返す。
  GameConfig selectConfig(String difficulty);


  //難易度ごとに出現する敵情報のリストを取得。
  //inner join(内部結合)：テーブルとテーブルを結合するためのSQL文。inner join　何と結合しますか？
  //inner join game_config→game_config側に登録されていないenemyは絶対に出ない。堅牢性が高まる。
  //on 何を条件に結合しますか？
  @Select("select * from spawn_enemy inner join game_config on spawn_enemy.difficulty = game_config.difficulty where spawn_enemy.difficulty = #{difficulty} order by spawn_enemy.id asc")
    //抽出したプレイヤースコア情報をマッピングしてセレクトリストにして返す。
  List<SpawnEnemy> selectSpawnEnemyList(String difficulty);

  //①GameConfigオブジェクトから、ゲーム時間(gameTime)と難易度(difficulty)についての数値が、
  //game_configテーブルのgame_timeカラムとdifficultyカラムに挿入される。
  @Insert("insert game_config(game_time, difficulty) values(#{gameTime},#{difficulty}))")
  //GameConfigオブジェクトをうけとりgame_configテーブルに挿入。
  //intは挿入された行数を返す。
  int insertConfig(GameConfig config);
}

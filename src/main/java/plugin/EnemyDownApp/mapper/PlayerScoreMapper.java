package plugin.EnemyDownApp.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import plugin.EnemyDownApp.mapper.data.GameConfig;
import plugin.EnemyDownApp.mapper.data.PlayerScore;


@Mapper
public interface PlayerScoreMapper {

  @Select("select * from player_score order by id asc")
    //抽出したプレイヤースコア情報をマッピングしてセレクトリストにして返す。
  List<PlayerScore> selectPlayerScoreList();

}

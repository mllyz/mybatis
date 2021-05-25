package mytest.mapper;

import mytest.entity.Actor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActorMapper {

  List<Actor> getActor(@Param("id")int id);
}
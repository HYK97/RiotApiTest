package hello.lol.repository;

import hello.lol.Entity.Summoner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummonerRepository extends JpaRepository<Summoner,Long> {
    Summoner findByName(String name);

}

package hello.lol.service;

import hello.lol.Entity.Summoner;
import hello.lol.dto.SummonerDto;
import hello.lol.repository.SummonerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummonerService {
    private final SummonerRepository summonerRepository;
    public void saveUser(SummonerDto dto) {
        summonerRepository.save(dto.toEntity());
    }

    public SummonerDto findUserInfo(String name) {
        Summoner summoner = summonerRepository.findByName(name);
        return summoner.toDto();
    }
}

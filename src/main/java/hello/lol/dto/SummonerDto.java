package hello.lol.dto;

import hello.lol.Entity.Summoner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummonerDto {
    private Long no;
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private String revisionDate;
    private int summonerLevel;

    public Summoner toEntity() {
        return Summoner.builder()
                .id(id)
                .accountId(accountId)
                .puuid(puuid)
                .name(name)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .summonerLevel(summonerLevel)
                .build();
    }
}

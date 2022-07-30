package hello.lol.Entity;

import hello.lol.dto.SummonerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Summoner {
    @Id
    @GeneratedValue
    @Column(name = "no", nullable = false)
    private Long no;
    private String id;
    private String accountId;
    private String puuid;
    private String name;
    private int profileIconId;
    private String revisionDate;
    private int summonerLevel;

    public SummonerDto toDto() {
        return SummonerDto.builder()
                .accountId(accountId)
                .id(id)
                .summonerLevel(summonerLevel)
                .name(name)
                .puuid(puuid)
                .profileIconId(profileIconId)
                .revisionDate(revisionDate)
                .no(no)
                .build();
    }

}

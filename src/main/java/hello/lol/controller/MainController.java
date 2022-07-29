package hello.lol.controller;


import hello.lol.Entity.Summoner;
import hello.lol.dto.SummonerDto;
import hello.lol.service.SummonerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Validated
@RestController
@RequestMapping(value = "/search")
@RequiredArgsConstructor
public class MainController {
    @Value("${api-key}")
    private String apikey;

    private final SummonerService summonerService;


    @GetMapping("/summerName/{name}")
    public ResponseEntity<SummonerDto> summonerNameSearch(@PathVariable @Length(min = 1, max = 17, message = "1이하 17이상으로 작성하세요") String name) {
        System.out.println("name = " + name);

        URI uri = UriComponentsBuilder
                .fromUriString("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/") //http://localhost에 호출
                .path(name)
                .queryParam("api_key", apikey)
                .encode()
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SummonerDto> summonerInfo = restTemplate.getForEntity(uri, SummonerDto.class);
        summonerService.saveUser(Objects.requireNonNull(summonerInfo.getBody()));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(summonerInfo.getBody());
        //nonblocking webflux 방식
      /*  WebClient client = WebClient.create();
        StringBuffer buffer = new StringBuffer("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/").append(name).append("?api_key=").append(apikey);
        Mono<String> stringMono = client.get()
                .uri(buffer.toString())
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(stringMono.flux().toStream().findFirst());*/
    }

}

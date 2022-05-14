package fi.maro.wiktionaryproxy.controller;

import fi.maro.wiktionaryproxy.model.WikiSite;
import fi.maro.wiktionaryproxy.service.WiktionaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class WikiController {

    WiktionaryService service;

    public WikiController(WiktionaryService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public WikiSite search(@RequestParam String word, @RequestParam(defaultValue = "Finnish") String lang) {
        return service.search(word, lang);
    }
}

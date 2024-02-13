package com.vtxlab.project.bc_crypto_coingecko.infra;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class ApiUtil {

  public static UriComponentsBuilder uriBuilder(UriScheme uriScheme,
      String domain, String endpoint, MultiValueMap<String, String> queryParams,
      String... pathSegments) {
    return UriComponentsBuilder.newInstance()//
        .scheme(uriScheme.getProtocol())//
        .host(domain)//
        .pathSegment(pathSegments)//
        .path(endpoint)//
        .queryParams(queryParams);
  }
}

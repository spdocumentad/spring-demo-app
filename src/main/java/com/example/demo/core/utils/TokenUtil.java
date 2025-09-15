package com.example.demo.core.utils;



import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenUtil {
//    /**
//     * Retrieves a specific claim from the JWT token present in the security context.
//     *
//     * @param claimName the name of the claim to retrieve
//     * @return the value of the claim as a {@link String}
//     * @throws ClaimNotFoundException if the token or the claim is not found
//     */
//    public String getClaim(String claimName) {
//        Jwt jwt = getToken().orElseThrow(() -> new ClaimNotFoundException(claimName));
//
//        String claimValue = jwt.getClaim(claimName);
//        if (claimValue == null) throw new ClaimNotFoundException(claimName);
//
//        return claimValue;
//    }
//
//    /**
//     * Retrieves the JWT token from the current authentication context if available.
//     *
//     * @return an {@link Optional} containing the {@link Jwt} token, or empty if not authenticated with JWT
//     */
//    private Optional<Jwt> getToken() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken)
//            return Optional.of(jwtAuthenticationToken.getToken());
//
//        return Optional.empty();
//    }

    public UUID getUserId() {
        // generate a random UUID for demonstration purposes
        // In a real application, you would retrieve this from the security context or authentication token
        return UUID.randomUUID();
    }
}

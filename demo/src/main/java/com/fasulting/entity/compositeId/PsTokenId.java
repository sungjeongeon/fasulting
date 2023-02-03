package com.fasulting.entity.compositeId;

import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.TokenEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsTokenId implements Serializable {

    @EqualsAndHashCode.Include
    public PsEntity ps;

    @EqualsAndHashCode.Include
    public TokenEntity token;
}
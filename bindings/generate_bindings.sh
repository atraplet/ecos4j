#! /bin/bash

# main
USAGE="\
Usage: generate_bindings path_to_ecos_source_package path_to_jextract_binary"

# read command line arguments
if [ $# -eq 2 ]; then
  ECOS4J=$(dirname "${0}")/../
  ECOS="${1}"
  JEXTRACT="${2}"
else
  echo "$USAGE"
  exit 1
fi

# remove old bindings
rm -rf "${ECOS4J}"/src/main/java/com/ustermetrics/ecos4j/bindings/

# generate bindings
$JEXTRACT \
  -D DLONG \
  -D LDL_LONG \
  -DSuiteSparse_long="long long" \
  -I "${ECOS}"/external/SuiteSparse_config \
  -l ecos \
  --target-package com.ustermetrics.ecos4j.bindings \
  --output "${ECOS4J}"/src/main/java \
  @"${ECOS4J}"/bindings/includes.txt "${ECOS}"/include/ecos.h

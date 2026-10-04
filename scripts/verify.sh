#!/bin/bash

cd "$(dirname "$0")"
source build.conf
cd ..

set -e # stop on error


verify_proof() {
	java -cp "$BIN_DIR" main/VerifierMain ExprEngine -in $1
}


verify_proof "resources/machine_proofs/proof_5.txt"
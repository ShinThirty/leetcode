#!/bin/bash
set -e

usage() {
  echo "Usage: ./add.sh <problem-number> [language]"
  echo ""
  echo "Languages: py (default), scala, go, cpp"
  echo ""
  echo "Examples:"
  echo "  ./add.sh 42"
  echo "  ./add.sh 42 go"
  echo "  ./add.sh 42 cpp"
  exit 1
}

[ -z "$1" ] && usage

NUM="$1"
LANG="${2:-py}"

case "$LANG" in
  py)    EXT="py" ;;
  scala) EXT="scala" ;;
  go)    EXT="go" ;;
  cpp)   EXT="cpp" ;;
  *)     echo "Unknown language: $LANG"; usage ;;
esac

DIR="problems/$NUM"
FILE="$DIR/solution.$EXT"

mkdir -p "$DIR"

if [ -f "$FILE" ]; then
  echo "$FILE already exists"
else
  touch "$FILE"
  echo "Created $FILE"
fi

# Open in editor: prefer nvim, fall back to $EDITOR
if command -v nvim &>/dev/null; then
  nvim "$FILE"
elif [ -n "$EDITOR" ]; then
  "$EDITOR" "$FILE"
fi

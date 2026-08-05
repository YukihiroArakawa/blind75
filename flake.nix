{
  description = "Development environment for Blind 75 solutions";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-unstable";

  outputs = { nixpkgs, ... }:
    let
      systems = [ "aarch64-darwin" "aarch64-linux" "x86_64-darwin" "x86_64-linux" ];
    in
    {
      devShells = nixpkgs.lib.genAttrs systems (system:
        let
          pkgs = nixpkgs.legacyPackages.${system};
          mdview = pkgs.writeShellApplication {
            name = "mdview";
            runtimeInputs = [ pkgs.mdserve ];
            text = ''
              if [ "$#" -eq 0 ]; then
                echo "Usage: mdview <Markdown file or directory> [mdserve options]" >&2
                exit 2
              fi

              exec mdserve --open --port "''${MDSERVE_PORT:-3001}" "$@"
            '';
          };
          leetcode = pkgs.writeShellApplication {
            name = "leetcode";
            runtimeInputs = [ pkgs.nodejs_22 pkgs.glib pkgs.libsecret ];
            text = ''
              export LD_LIBRARY_PATH="${pkgs.lib.makeLibraryPath [ pkgs.glib pkgs.libsecret ]}''${LD_LIBRARY_PATH:+:$LD_LIBRARY_PATH}"
              exec npx --yes --package=@night-slayer18/leetcode-cli@3.3.0 leetcode "$@"
            '';
          };
        in
        {
          default = pkgs.mkShell {
            packages = with pkgs; [
              direnv
              fzf
              jdk21
              mdserve
              dotnet-sdk_10
              csharpier
              python3
              nodejs_22
            ] ++ [ mdview leetcode ];
          };
        });
    };
}

# SlashServer

[![Build Status](https://img.shields.io/github/workflow/status/N3FS/SlashServer/Build%20SlashServer/master?logo=github&label=build)](https://github.com/N3FS/SlashServer/actions/workflows/build.yml)
![Velocity 1.0](https://img.shields.io/badge/Velocity-3.0.0-0288d1.svg)

## Usage

This plugin adds /server command aliases on Velocity. Each server command requires the `slashserver.<name>` permission.

For example:  
- `/lobby` is an alias of `/server lobby` and requires the `slashserver.lobby` permission.
- `/survival` is aliased to `/server survival` and requires the `slashserver.survival` permission.

In addition, you can reload the plugin using the `/ssreload` command, which requires the `slashserver.reload` permission
and will re-register the server commands.

## Building

Clone the repo locally, then run `gradlew build`. The plugin jar can then be found under `build/libs/SlashServer-<version>.jar`.

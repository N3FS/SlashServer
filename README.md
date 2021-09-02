# SlashServer

[![Build Status](https://ci.noxal.net/job/SlashServer/badge/icon)](https://ci.noxal.net/job/SlashServer/)
![Velocity 1.0](https://img.shields.io/badge/Velocity-3.0.0-green.svg)

## Usage

This plugin adds /server command aliases on Velocity. Each server command requires the `slashserver.<name>` permission.

For example:  
- `/lobby` is an alias of `/server lobby` and requires the `slashserver.lobby` permission.
- `/survival` is aliased to `/server survival` and requires the `slashserver.survival` permission.

In addition, you can reload the plugin using the `/ssreload` command, which requires the `slashserver.reload` permission
and will re-register the server commands.

## Building

Clone the repo locally, then run `gradlew build`.

export interface jitCallPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}

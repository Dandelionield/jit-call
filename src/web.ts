import { WebPlugin } from '@capacitor/core';

import type { jitCallPlugin } from './definitions';

export class jitCallWeb extends WebPlugin implements jitCallPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

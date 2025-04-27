import { registerPlugin } from '@capacitor/core';

import type { jitCallPlugin } from './definitions';

const jitCall = registerPlugin<jitCallPlugin>('jitCall', {
  web: () => import('./web').then((m) => new m.jitCallWeb()),
});

export * from './definitions';
export { jitCall };
